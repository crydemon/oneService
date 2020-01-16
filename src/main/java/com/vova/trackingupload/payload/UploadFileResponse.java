package com.vova.trackingupload.payload;

import lombok.Data;


@Data
public class UploadFileResponse {
    private String info;

    public UploadFileResponse(String info) {
        this.info = info;
    }
}
