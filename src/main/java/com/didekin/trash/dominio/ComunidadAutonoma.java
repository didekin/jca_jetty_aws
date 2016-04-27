package com.didekin.trash.dominio;

/**
 * User: pedro@didekin
 * Date: 26/04/16
 * Time: 18:22
 */
/* comunidad_autonoma */
public class ComunidadAutonoma {

    /* ca_id */
    private final long comunidadId;
    /* nombre */
    private final String nombre;

    public ComunidadAutonoma(long comunidadId, String nombre)
    {
        this.comunidadId = comunidadId;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComunidadAutonoma that = (ComunidadAutonoma) o;

        return comunidadId == that.comunidadId && nombre.equals(that.nombre);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (comunidadId ^ (comunidadId >>> 32));
        result = 31 * result + nombre.hashCode();
        return result;
    }
}
