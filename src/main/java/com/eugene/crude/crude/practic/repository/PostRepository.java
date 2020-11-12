package com.eugene.crude.crude.practic.repository;


import com.eugene.crude.crude.practic.model.Post;



import java.util.List;

public interface PostRepository extends Genericrepository<Post, Long>  {
    @Override
    Post getById(Long aLong);

    @Override
    List<Post> getAll();

    @Override
    Post save(Post post);

    @Override
    Post update(Post post);

    @Override
    void deleteById(Long aLong);


}
