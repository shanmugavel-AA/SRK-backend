package com.example.SRK.Backend.Repository;

import com.example.SRK.Backend.Model.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<FormSubmission,Long> {
}
