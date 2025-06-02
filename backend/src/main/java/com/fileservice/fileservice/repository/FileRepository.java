package com.fileservice.fileservice.repository;

import com.fileservice.fileservice.model.File;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FileRepository extends CassandraRepository<File, UUID> {

}
