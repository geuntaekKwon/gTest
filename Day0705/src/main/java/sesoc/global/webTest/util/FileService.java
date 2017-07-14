package sesoc.global.webTest.util;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileService {
	public static String saveFile(MultipartFile upload, String uploadPath) {
		// false이면 파일이 전송되었으므로 복사작업
		File path = new File(uploadPath);
		// 저장 디렉토리가 없으면 디렉토리 생성
		if (!path.isDirectory()) {
			path.mkdir();
		} // inner if

		String originalFileName = upload.getOriginalFilename();
		String temp = UUID.randomUUID().toString();

		// 시간으로 하기
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss"); String
		 * temp2 = sdf.format(new Date());
		 */
		//

		String fileName = ""; // 확장명을 뺀 파일명
		String ext = ""; // 파일명을 뺸 확장명
		String savedFileName = ""; // HDD에 저장되는 이름 : filename + _ + UUID + . +
									// ext;

		int lastIndex = originalFileName.lastIndexOf(".");
		fileName = originalFileName.substring(0, lastIndex);

		// 확장자가 없으면 -1 리턴
		if (lastIndex == -1) {
			ext = "";
		} else {
			ext = originalFileName.substring(lastIndex + 1);

			// HDD에 저장할 savedFileName 작성
			savedFileName = fileName + "_" + temp + "." + ext;
			System.out.println("[ savedFileName ] : " + originalFileName + ", " + savedFileName);

			// savedFileName = fileName + "_" + temp2;
			// System.out.println("[ savedFileName2 ] : " + originalFileName +
			// ", " + savedFileName + "." + ext);

			// 파일 객체 생성 : 경로 + 파일명
			File serverFile = null;
			serverFile = new File(uploadPath + "/" + savedFileName);

			// 파일 저장만 함
			try {
				upload.transferTo(serverFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // else
		return savedFileName;
	}// saveFile
	
	public static String getSavedFileName(MultipartFile upload, String uploadPath){
		String originalFileName = upload.getOriginalFilename();
		String temp = UUID.randomUUID().toString();
		
		int lastIndex = originalFileName.lastIndexOf(".");
		String ext = originalFileName.substring(lastIndex);
		String savedFileName = originalFileName.substring(0, lastIndex)+ "_" + temp + ext;
		return savedFileName;
	}//getSavedFileName
	
	// 삭제
	/**
	 * @param fullPath = 경로 + 파일명
	 * @return
	 */
	public static boolean deleteFile(String fullPath){
		boolean result = false;
		if(fullPath != null){
			File delFile = new File(fullPath);
			if(delFile.isFile()){
				result = delFile.delete();
			}//inner if
		}//if
		return result;
	}//deleteFile
	
	public static boolean updateFile(MultipartFile upload, String uploadPath, String fullPath){
		boolean result = false;
		File serverFile = null;
		serverFile = new File(uploadPath + "/" + fullPath);

		// 파일 저장만 함
		try {
			upload.transferTo(serverFile);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}//updateFile
	
	
}// class
