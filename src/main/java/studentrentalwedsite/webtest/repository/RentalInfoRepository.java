package studentrentalwedsite.webtest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studentrentalwedsite.webtest.model.OAuth2UserEntity;
import studentrentalwedsite.webtest.model.RentalInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalInfoRepository extends MongoRepository<RentalInfo, String> {

    // test
    List<RentalInfo> findByAddressContaining(String keyword);
    List<RentalInfo> findByAllowPet(int allowPet);
}