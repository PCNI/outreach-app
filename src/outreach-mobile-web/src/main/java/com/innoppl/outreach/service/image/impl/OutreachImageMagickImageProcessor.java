package com.innoppl.outreach.service.image.impl;

import com.innoppl.outreach.service.image.OutreachImageProcessor;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.process.ProcessExecutor;
import org.im4java.process.ProcessTask;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jerald Mejarla
 */
@Service("OutreachImageProcessor")
public class OutreachImageMagickImageProcessor implements OutreachImageProcessor {

    @Override
    public void resize(final String srcImage, final String dstImage,
            final int width, final int height) throws Exception {
        final ConvertCmd cmd = new ConvertCmd();
        final IMOperation op = new IMOperation();
        final Info info = new Info(srcImage, true);
        final int imageWidth = info.getImageWidth();
        final int imageHeight = info.getImageHeight();
        if (imageWidth > width && imageHeight > height) {
            op.addImage();
            op.autoOrient();
            op.resize(width, height);
            op.addImage();
            cmd.run(op, srcImage, dstImage);
        } else {
            FileUtils.copyFile(new File(srcImage), new File(dstImage));
        }
    }

    @Override
    public void resizeAllImages(final Map<String, String> images,
            final int width, final int height)
            throws Exception {
        final ProcessExecutor exec = new ProcessExecutor();
        final IMOperation op = new IMOperation();
        op.addImage();
        op.autoOrient();
        op.resize(width, height);
        op.addImage();
        final Set<String> srcImageSet = images.keySet();
        for (String img : srcImageSet) {
            final ConvertCmd cmd = new ConvertCmd();
            final ProcessTask processTask
                    = cmd.getProcessTask(op, img, images.get(img));
            exec.execute(processTask);
        }
        exec.shutdown();
        if (!exec.awaitTermination(10, TimeUnit.SECONDS)) {
            exec.shutdownNow();
        }
    }
}
