package com.roman.forum.repository;

import com.roman.forum.model.Topic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {

    @Transactional
    @Modifying
    @Query("update Topic t set t.likes = :likes, t.dislikes = :dislikes where t.id = :id")
    void updateLikesDislikes(@Param("likes") Integer likes, @Param("dislikes") Integer dislikes, @Param("id") UUID id);
}
