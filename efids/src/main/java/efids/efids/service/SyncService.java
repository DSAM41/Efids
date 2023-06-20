package efids.efids.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.efids.model.Contingency_dep;
import efids.efids.model.Sync;
import efids.efids.repository.SyncRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class SyncService {

	@Autowired
	SyncRepository syncRepo;
	
	// READ
	public String getData() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(syncRepo.findAll());
		return json;
	}

	// CREATE
	public Sync syncData(Sync model) {
		Optional<Sync> sync = syncRepo.findById(model.getId());
		if (model.getStatus_sync() != null) {
			sync.get().setStatus_sync(model.getStatus_sync());
		}
		if (model.getTime() != null) {
			sync.get().setTime(model.getTime());
		}
		return syncRepo.save(sync.get());
	}

//	public Person updatePerson(Long id, Person updatedPerson) {
//        Person person = personRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
//        if (updatedPerson.getName() != null) {
//            person.setName(updatedPerson.getName());
//        }
//        if (updatedPerson.getLastname() != null) {
//            person.setLastname(updatedPerson.getLastname());
//        }
//        if (updatedPerson.getNic() != null) {
//            person.setNic(updatedPerson.getNic());
//        }
//        return personRepository.save(person);
//    }

}
