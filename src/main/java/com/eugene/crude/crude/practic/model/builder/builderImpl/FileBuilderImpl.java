package com.eugene.crude.crude.practic.model.builder.builderImpl;

import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.model.FileStatus;
import com.eugene.crude.crude.practic.model.builder.Builder;

public class FileBuilderImpl implements Builder {

    private Integer id;
    private String name;
    private byte[] fileByte;
    private FileStatus fileStatus;

    public FileBuilderImpl setId(Integer id) {
        this.id = id;
        return this;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public FileBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }
    public FileBuilderImpl setByteArray(byte[] fileByte) {
        this.fileByte = fileByte;
        return this;
    }
    public FileBuilderImpl setStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
        return this;
    }

    public FileBuilderImpl() {

    }
    public FileBuilderImpl(Integer id, String content, FileStatus fileStatus, byte [] fileByte ) {
        this.id = id;
        this.name = content;
        this.fileStatus=fileStatus;
        this.fileByte= fileByte;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public File build(){
        return  new File(this);
    }
}
