package com.kiibos.mongoexport.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MongoBatchImportService {


    String batchImport(HttpServletRequest request);
}
