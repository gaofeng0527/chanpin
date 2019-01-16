package com.eduedu.chanpin.service.impl;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class TemplateService implements com.eduedu.chanpin.service.TemplateService {
    @Override
    public boolean render(InputStream template, OutputStream out, Map<String, Object> params) throws Exception {
        VelocityContext ctx=new VelocityContext(params);
        Writer writer=new OutputStreamWriter(out,"utf-8");
        boolean status= Velocity.evaluate(ctx, writer, "input stream render", new InputStreamReader(template,"utf-8"));
        writer.flush();
        return status;
    }
}
