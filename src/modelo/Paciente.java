package modelo;

public class Paciente extends Persona {

    private String fechaNacimiento;
    private String obraSocial;

    public Paciente(int dni,
                    String nombreCompleto,
                    String fechaNacimiento,
                    String obraSocial) {

        super(dni, nombreCompleto);

        this.fechaNacimiento = fechaNacimiento;
        this.obraSocial = obraSocial;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    @Override
    public void mostrarDatos() {

        System.out.println("DNI: " + dni);

        System.out.println(
                "Nombre: "
                + nombreCompleto);

        System.out.println(
                "Fecha Nacimiento: "
                + fechaNacimiento);

        System.out.println(
                "Obra Social: "
                + obraSocial);
    }
}