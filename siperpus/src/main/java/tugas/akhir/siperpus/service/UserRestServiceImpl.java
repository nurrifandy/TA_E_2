package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.repository.UserDb;
import tugas.akhir.siperpus.rest.Setting;
import tugas.akhir.siperpus.rest.UserDetail;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{
    private final WebClient webClient;

    @Autowired
    private UserDb userDb;

    @Override
    public List<UserModel> retriveListUser() {
        return userDb.findAll();
    }

    private static int getRandomIntegerWithinRange(int min, int max) {
        int spread = max - min;
        return new Random().nextInt(spread + 1) + min;
    }

    @Override
    public String generateNIP(UserDetail userDetail, UserModel userModel){
        String newKode = new String();
        String nampung1 = "P";
        newKode+=nampung1;
        String nampung2 = new String();
        char nam1 = userDetail.getTanggalLahir().charAt(0);
        char nam2 = userDetail.getTanggalLahir().charAt(1);
        char nam3 = userDetail.getTanggalLahir().charAt(2);
        char nam4 = userDetail.getTanggalLahir().charAt(3);
        String nampungs1 = String.valueOf(nam1)+String.valueOf(nam2)+String.valueOf(nam3)+String.valueOf(nam4);
        char nam5 = userDetail.getTanggalLahir().charAt(5);
        char nam6 = userDetail.getTanggalLahir().charAt(6);
        String nampungs2 = String.valueOf(nam5) + String.valueOf(nam6);
        char nam7 = userDetail.getTanggalLahir().charAt(8);
        char nam8 = userDetail.getTanggalLahir().charAt(9);
        String nampungs3 = String.valueOf(nam7)+String.valueOf(nam8);
        nampung2 += nampungs3;
        nampung2 += nampungs2;
        nampung2 += nampungs1 ;
        newKode+=nampung2;

        int lowerLimit = 97;
        int upperLimit = 122;
        Random random = new Random();
        StringBuffer nampung3 = new StringBuffer(2);
        for (int i = 0; i < 2; i++) {
            int nextRandomChar = lowerLimit+ (int)(random.nextFloat() * (upperLimit - lowerLimit + 1));
            nampung3.append((char)nextRandomChar);
        }
        String namps = new String();
        namps += nampung3;
        newKode+=namps.toUpperCase();
        int randomIntegerRange = getRandomIntegerWithinRange(100, 999);
        String nampung4 = String.valueOf(randomIntegerRange);
        newKode+=nampung4;
        String nampung5= userModel.getUuid();
        newKode+=nampung5;
        return newKode;
    }

    @Override
    public Mono<String> register (UserDetail userDetail, UserModel userModel) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("idUser", userModel.getUuid());
        data.put("nip", generateNIP(userDetail, userModel));
        data.put("nama", userDetail.getNama());
        data.put("tempatLahir", userDetail.getTempatLahir());
        data.put("tanggalLahir", String.valueOf(userDetail.getTanggalLahir()));
        data.put("alamat", userDetail.getAlamat());
        data.put("telepon", userDetail.getTelepon());
        String uri = "/employees";
        return this.webClient
                .post()
                .uri(uri)
                .syncBody(data)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
    }

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.sivitasUrl).build();
    }

}
