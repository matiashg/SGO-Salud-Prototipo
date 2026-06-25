package modelo;

public class Medico extends Persona {

    private int idMedico;
    private String matricula;

    public Medico(int idMedico,
                  int dni,
                  String nombreCompleto,
                  String matricula) {

        super(dni, nombreCompleto);

        this.idMedico = idMedico;
        this.matricula = matricula;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public void mostrarDatos() {

        System.out.println(
                "Médico: "
                + nombreCompleto);

        System.out.println(
                "Matrícula: "
                + matricula);
    }
}