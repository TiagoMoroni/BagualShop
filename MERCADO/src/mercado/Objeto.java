
package mercado;
    
import java.util.Date;

public class Objeto {
    
    private int preço;
    private String foto;
    private String categoria;
    private static Date dataanuncio;

    public int getPreço() {
        return preço;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static Date getDataanuncio() {
        return dataanuncio;
    }

    public static void setDataanuncio(Date dataanuncio) {
        Objeto.dataanuncio = dataanuncio;
    }

    public Objeto(int preço, String foto, String categoria) {
        this.preço = preço;
        this.foto = foto;
        this.categoria = categoria;
    }           
    
}
