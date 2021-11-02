package com.usa.reto3.Services;

import com.usa.reto3.Model.Lib;
import com.usa.reto3.Repository.LibRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviciosLib {

    @Autowired
    private LibRepositorio metodosCrud;

    public List<Lib> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Lib> getLib(int id) {
        return metodosCrud.getLib(id);
    }

    public Lib save(Lib lib) {
        if (lib.getId() == null) {
            return metodosCrud.save(lib);
        } else {
            Optional<Lib> evt = metodosCrud.getLib(lib.getId());
            if (evt.isEmpty()) {
                return metodosCrud.save(lib);
            } else {
                return lib;
            }
        }
    }

    public Lib update(Lib lib) {
        if (lib.getId() != null) {
            Optional<Lib> e = metodosCrud.getLib(lib.getId());
            if (!e.isEmpty()) {
                if (lib.getName() != null) {
                    e.get().setName(lib.getName());
                }
                if (lib.getTarget() != null) {
                    e.get().setTarget(lib.getTarget());
                }
                if (lib.getCapacity() != null) {
                    e.get().setCapacity(lib.getCapacity());
                }
                if (lib.getDescription() != null) {
                    e.get().setDescription(lib.getDescription());
                }
                if (lib.getCategory() != null) {
                    e.get().setCategory(lib.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            } else {
                return lib;
            }
        } else {
            return lib;
        }
    }

    public boolean deleteLib(int id) {
        Boolean aBoolean = getLib(id).map(lib -> {
            metodosCrud.delete(lib);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
