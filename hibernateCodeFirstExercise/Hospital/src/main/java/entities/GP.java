package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gps")
public class GP extends BaseEntity {

    @OneToMany(targetEntity = Visitation.class, mappedBy = "gp")
    private Set<Visitation> visitations;

    @ManyToMany
    @JoinTable(name = "gps_diagnoses",
            joinColumns = @JoinColumn(name = "gp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
    private Set<Diagnose> diagnoses;

    @ManyToMany
    @JoinTable(name = "gps_medicaments",
            joinColumns = @JoinColumn(name = "gp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> medicaments;

    @OneToMany(targetEntity = Patient.class, mappedBy = "gp")
    private Set<Patient> patients;

    public GP() {}

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

}
