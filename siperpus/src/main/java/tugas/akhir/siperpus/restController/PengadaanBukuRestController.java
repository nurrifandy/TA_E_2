package tugas.akhir.siperpus.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.service.PengadaanBukuRestService;
import tugas.akhir.siperpus.service.UserRestService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PengadaanBukuRestController {
    @Autowired
    private PengadaanBukuRestService pengadaanBukuRestService;

    @PostMapping(value = "/addProcurement")
    private PengadaanBukuModel addProcurement(@Valid @RequestBody PengadaanBukuModel pengadaanBuku, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            return
                    pengadaanBukuRestService.addProcurement(pengadaanBuku);
        }
    }
}
