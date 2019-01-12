package cdtu.wheretobuy.controller;

import cdtu.wheretobuy.pojo.Result;
import cdtu.wheretobuy.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


@RestController
@CrossOrigin
public class UploadController {
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;

	public String getFastDfsFileCOnfig() throws IOException {
		File file = new File("/root/temp/fdfs_client.conf");
		if (!file.exists()) {
			//file.createNewFile();
			System.out.println(file.toPath());
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("/config/fdfs_client.conf");
			Files.copy(is, file.toPath());
		}
		System.out.println(file.getAbsolutePath()+"------------------");
		return file.getAbsolutePath();
	}
	@RequestMapping("/upload")
	public Result upload(MultipartFile file){
		try {



//			FastDFSClient client=new FastDFSClient("classpath:config/fdfs_client.conf");
			FastDFSClient client=new FastDFSClient(getFastDfsFileCOnfig());
			String originalFilename = file.getOriginalFilename();
//			System.out.println(originalFilename+"2222222222");
			String ext = file.getOriginalFilename().substring(originalFilename.lastIndexOf(".")+1);
			String path = client.uploadFile(file.getBytes(), ext);
			String url=FILE_SERVER_URL+path;
			return new Result(true, url);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, "上传失败");
		}
	}
}
