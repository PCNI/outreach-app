package com.innoppl.outreach.service.image;

import java.util.Map;

/**
 *
 * @author Jerald Mejarla
 */
public interface OutreachImageProcessor {

    /**
     *
     * @param srcImage
     * @param dstImage
     * @param width
     * @param height
     * @throws Exception
     */
    void resize(final String srcImage, final String dstImage,
            final int width, final int height) throws Exception;

     /**
     *
     * @param images
     * @param width
     * @param height
     * @throws Exception
     */
    void resizeAllImages(final Map<String, String> images,
            final int width, final int height) throws Exception;
}
