package dambi.pojoak.produktua;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Produktuak" )
public class Produktuak {
    List<Produktua> produktuak;

    public List<Produktua> getProduktuak()
    {
        return produktuak;
    }

    @XmlElement( name = "Produktua" )
    public void setProduktuak( List<Produktua> produktuak )
    {
        this.produktuak = produktuak;
    }

    public void add( Produktua produktua )
    {
        if( this.produktuak == null )
        {
            this.produktuak = new ArrayList<Produktua>();
        }
        this.produktuak.add( produktua );

    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer();
        for( Produktua produktua : this.produktuak )
        {
            str.append( produktua.toString() );
        }
        return str.toString();
    }
}
