package com.unistrong.geotsd.datasource.data;

public class FastDFSFile {
    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;

    public FastDFSFile() {
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getExt() {
        return ext;
    }

    public String getMd5() {
        return md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
