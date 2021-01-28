package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.FileBuilderImpl;

import javax.persistence.*;

@Entity
@Table(name="file")
public class File {

public File (FileBuilderImpl fileBuilder){
    id =fileBuilder.getId();
    name= fileBuilder.getName();
    fileByte=fileBuilder.getFileByte();
    fileStatus=fileBuilder.getFileStatus();
}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "file_byte_array")
    private byte[] fileByte;
    @Column(name="file_status")
    @Enumerated(EnumType.ORDINAL)
    private FileStatus fileStatus;

    public File() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }



}