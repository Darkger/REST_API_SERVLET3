package com.eugene.crude.crude.practic.repository.repositoryIO;



import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.repository.Genericrepository;
import com.eugene.crude.crude.practic.utils.IOUtils;
import com.eugene.crude.crude.practic.repository.RegionRepository;

import com.google.gson.Gson;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {


    private Path regionFilePath =Paths.get("C:\\javaFiles\\region.json"); ;
    private Gson gson = new Gson();



    @Override
    public Region getById(Long id) {
        try {
            List<String> listReg = Files.readAllLines(regionFilePath);
            if (!listReg.isEmpty()) {
                for (String str : listReg) {
                    if (str.contains("\"id\":\"" + id + "\"")) {
                        Region region = gson.fromJson(str, Region.class);
                        return region;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Region> getAll() {
        try {
            List<String> listReg = Files.readAllLines(regionFilePath);
            List<Region> listRegionObj = new ArrayList<>();

            if (!listReg.isEmpty()) {
                for (String str : listReg) {
                    Region region = gson.fromJson(str, Region.class);
                    listRegionObj.add(region);
                }
                return listRegionObj;
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Region save(Region region) {
        Genericrepository genericrepository = new PostRepositoryImpl();
        try {
            if (!Files.exists(regionFilePath)) {
                Files.createFile(regionFilePath);
            }
            List<String> listReg = Files.readAllLines(regionFilePath);
            for( String str: listReg)
            {
                if(str.contains(region.getContent())){
                return   gson.fromJson(str,Region.class);
                }
            }

            if (region.getId()==null)
                region.setId("0");

            int validId = IOUtils.getValidId(listReg, Integer.parseInt(region.getId()),Region.class);
            region.setId(String.valueOf(validId));
            try {
                String strToWrite = gson.toJson(region);
                Files.writeString(regionFilePath, strToWrite + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("ошибка записи в файл3");
            }
            return region;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Region update(Region post) {
        try {
            List<String> listReg = Files.readAllLines(regionFilePath);

            String fdf = "\"id\":\"" + post.getId() + "\"";
            if (!listReg.isEmpty()) {
                for (int i = 0; i < listReg.size(); i++) {

                    if (listReg.get(i).contains(fdf)) {
                        Region regNew = gson.fromJson(listReg.get(i), Region.class);
                        regNew.setContent(post.getContent());
                        listReg.set(i, gson.toJson(regNew));
                        break;
                    }
                }
                Files.delete(regionFilePath);
                Files.createFile(regionFilePath);
                for (String str : listReg) {
                    Files.writeString(regionFilePath, str + "\n", StandardOpenOption.APPEND);
                }
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            List<String> listReg = Files.readAllLines(regionFilePath);

            for (int i = 0; i < listReg.size(); i++) {
                if (listReg.get(i).contains(("\"id\":\"" + id + "\""))) {
                    listReg.set(i, "DELETE");
                }
            }
            Files.delete(regionFilePath);
            Files.createFile(regionFilePath);
            listReg.stream().forEach(str -> {
                if (!str.equals("DELETE")) {
                    try {
                        Files.writeString(regionFilePath, str + "\n", StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
