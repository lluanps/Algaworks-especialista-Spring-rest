package com.luan.algafoodapi.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.luan.algafoodapi.core.storage.StorageProperties;
import com.luan.algafoodapi.domain.service.FotoStorageService;
import com.luan.algafoodapi.infrastructure.service.storage.exception.copy.StorageException;

@Service
public class S3FotoStorageService implements FotoStorageService {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
			
			var objectMetaData = new ObjectMetadata();
			objectMetaData.setContentType(novaFoto.getContentType()); // fixar o content type para apenas visualizar,  sem isso ao clicar no link da imagem no s3 o arquivo será baixado
			
			//payload
			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(),//nome do bucket
					caminhoArquivo, //caminho que o objeto esta
					novaFoto.getInputStream(), // inputstream
					objectMetaData) //objectmetadata
				.withCannedAcl(CannedAccessControlList.PublicRead); // usado para tornar aquivo publico 
			
			//realizar chamada
			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possivel enviar arquivo para Amazon S3.", e);
		}
	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
			
			var deleteObjectRequest = new DeleteObjectRequest(
					storageProperties.getS3().getBucket(),
					caminhoArquivo);
			
			amazonS3.deleteObject(deleteObjectRequest);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
		}
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);
		
		return FotoRecuperada.builder()
				.url(url.toString())
				.build();
	}
	

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}

}
