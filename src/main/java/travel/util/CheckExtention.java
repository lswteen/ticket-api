package travel.util;


public class CheckExtention {
	
	
	public static String[] imageFileWhiteListExtentions = new String[]{
			"jpg","png","jpeg","gif","bmp"
	};

    public static String[] imageAndDocumentFileWhiteListExtentions = new String[]{
            "txt", "rtf", "hwp", "pdf", "doc", "docx", "ppt", "pptx", "xls", "xlsx",
            "png", "jpg", "jpeg", "bmp", "gif", "pcx",
            "zip", "tar", "gz"
    };

	public static boolean isValidFileExtension(String fileName, String[] extensions) throws Exception {
		boolean result = false;
		if (fileName != null && !"".equals(fileName)
		&& isContainFileExtension(fileName, extensions)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private static boolean isContainFileExtension(String fileName, String[] extensions) throws Exception {
		boolean result = false;
		String fileExtension = getFileExtension(fileName);
		for (String ex : extensions) {
			if (fileExtension.equals(ex)) {
				result = true;
				break;
			}
		}
		return result;

	}

	private static String getFileExtension(String fileName) throws Exception {
		String fileExtension = "";
		if (fileName != null && !"".equals(fileName)) {
			if (fileName.lastIndexOf(".") != -1) {
				fileExtension = fileName.toLowerCase().substring(
				fileName.lastIndexOf(".") + 1, fileName.length());
			} else {
				fileExtension = "";
			}
		} else {
			fileExtension = "";
		}
		return fileExtension;
	}
}
