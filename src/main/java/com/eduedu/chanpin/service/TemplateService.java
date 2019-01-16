package com.eduedu.chanpin.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface TemplateService {

    /**
     * 直接通过输入流渲染模板到输出流，整个输入流和输出流都以UTF－8作为编码格式
     * @param template
     * @param out
     * @param params
     * @return
     * @throws Exception
     */
    boolean render(InputStream template, OutputStream out, Map<String,Object> params) throws Exception;
}
