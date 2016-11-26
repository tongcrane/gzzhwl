package com.gzzhwl.demo;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

import com.gzzhwl.patchca.color.SingleColorFactory;
import com.gzzhwl.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.gzzhwl.patchca.service.ConfigurableCaptchaService;
import com.gzzhwl.patchca.utils.encoder.EncoderHelper;

/**
 * sample code
 * Created by shijinkui on 15/3/15.
 */
public class Sample {
    public static void main(String[] args) throws IOException {

        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        FileOutputStream fos = new FileOutputStream("patcha_demo.png");
        EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
        fos.close();
    }
}
