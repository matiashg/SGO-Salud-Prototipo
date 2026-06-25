package modelo;

public class Turno {

    private String fechaTurno;
    private String horaTurno;

    private int dniPaciente;

    private int idMedico;

    public Turno(String fechaTurno,
                 String horaTurno,
                 int dniPaciente,
                 int idMedico) {

        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.dniPaciente = dniPaciente;
        this.idMedico = idMedico;
    }

    public String getFechaTurno() {
        return fechaTurno;
    }

    public String getHoraTurno() {
        return horaTurno;
    }

    public int getDniPaciente() {
        return dniPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }
}