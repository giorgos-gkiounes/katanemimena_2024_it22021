package gr.hua.dit.ds.ds_lab_2024.repositories;

import gr.hua.dit.ds.ds_lab_2024.entities.project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface projectRepository extends JpaRepository<project, Integer> {

    // Query to fetch only visible projects
    @Query("SELECT p FROM project p WHERE p.isVisible = true")
    List<project> findAllVisible();


}
