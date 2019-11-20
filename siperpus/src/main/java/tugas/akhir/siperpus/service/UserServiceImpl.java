package tugas.akhir.siperpus.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas.akhir.siperpus.model.RoleModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.repository.UserDb;
import tugas.akhir.siperpus.rest.UserDetail;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser (UserModel user){
        return userDb.save(user);
    }

    @Override
    public UserModel getUserByUuid(String uuid){
        List<UserModel> user = userDb.findByUuid(uuid);
        return user.get(0);
    }

    @Override
    public Optional<UserModel> getUserByRole (RoleModel role) { return userDb.findByRoleId(role.getId()); }

    @Override
    public UserModel createDummyUserPengadaanIfNotExist (RoleModel role) {
        if(getUserByRole(role).isEmpty()){
            UserModel userNya = new UserModel();
            userNya.setUsername("PengajuPengadaanBukuEksternal");
            userNya.setPassword("PasswordPengajuPengadaanBukuEksternal");
            userNya.setRole(role);
            return addUser(userNya);
        } else {
            return getUserByRole(role).get();
        }
    }

    private static int getRandomIntegerWithinRange(int min, int max) {
        int spread = max - min;
        return new Random().nextInt(spread + 1) + min;
    }

//    @Override
//    public String generateNIP(UserDetail userDetail){
//        String newKode = new String();
//        String nampung1 = "P";
//        newKode+=nampung1;
//        String nampung2 = String.valueOf(userDetail.getTanggalLahir());
//        newKode+=nampung2;
//        int lowerLimit = 97;
//        int upperLimit = 122;
//        Random random = new Random();
//        StringBuffer nampung3 = new StringBuffer(2);
//        for (int i = 0; i < 2; i++) {
//            int nextRandomChar = lowerLimit
//                    + (int)(random.nextFloat()
//                    * (upperLimit - lowerLimit + 1));
//            nampung3.append((char)nextRandomChar);
//        }
//        newKode+=nampung3;
//        int randomIntegerRange = getRandomIntegerWithinRange(10, 20);
//        String nampung4 = String.valueOf(randomIntegerRange);
//        newKode+=nampung4;
//        String nampung5= userDetail.getIdUser();
//        newKode+=nampung5;
//        return newKode.toUpperCase() ;
//    }
}
