package api.rest.file.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class file {

	/**
	 * Efetuar o download de um arquivo atravez de uma requisição GET
	 * Exemplo de como informar caminho do arquivo
	 * \\home\\documents\\arquivo.xlsx
	 * 
	 * Exemplo do nome apresentado antes do download
	 * arquivo.xlsx
	 * @return Arquivo solicitado para download
	 * @throws IOException 
	 */
	 @RequestMapping(value = "/download", method = RequestMethod.GET)
	    public HttpEntity<byte[]> download() throws IOException {
	    	HttpEntity<byte[]> entity =  new HttpEntity<byte[]>(null, null);
	    	try {
	    		byte[] arquivo = Files.readAllBytes( Paths.get("/home/CAMINHO DO ARQUIVO.EXTENSÃO") );
	    		
	    		HttpHeaders httpHeaders = new HttpHeaders();
	    		
	    		httpHeaders.add("Content-Disposition", "attachment;filename=\"NOME APRESENTADO ANTES DO DOWNLOAD\"");
	    		
	    		entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
	    		
	    	}
	    	catch (IOException e) {
	    		throw new IOException("Erro: Problemas para localizar arquivo solicitado!!!");
			}

    		return entity;
	    }
}
