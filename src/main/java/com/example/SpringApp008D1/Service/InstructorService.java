package com.example.SpringApp008D1.service;

import com.example.SpringApp008D1.model.InstructorModel;
import com.example.SpringApp008D1.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorModel> getAllInstructores() {
        return instructorRepository.findAll();
    }

    public Optional<InstructorModel> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public InstructorModel saveInstructor(InstructorModel instructor) {
        return instructorRepository.save(instructor);
    }

    public boolean updateInstructor(Long id, InstructorModel instructorData) {
        Optional<InstructorModel> instructorOpt = instructorRepository.findById(id);
        if (instructorOpt.isPresent()) {
            InstructorModel instructor = instructorOpt.get();
            instructor.setNombre(instructorData.getNombre());
            instructor.setEspecialidad(instructorData.getEspecialidad());
            instructor.setEmail(instructorData.getEmail());
            instructorRepository.save(instructor);
            return true;
        }
        return false;
    }

    public boolean deleteInstructor(Long id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
