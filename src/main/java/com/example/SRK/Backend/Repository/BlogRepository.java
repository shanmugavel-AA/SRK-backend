package com.example.SRK.Backend.Repository;

import com.example.SRK.Backend.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Blog b SET b.featured = false WHERE b.featured = true")
    void unsetAllFeatured();
}
