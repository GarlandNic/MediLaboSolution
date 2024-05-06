package com.projet9.notes.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projet9.notes.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, ObjectId> {

}
