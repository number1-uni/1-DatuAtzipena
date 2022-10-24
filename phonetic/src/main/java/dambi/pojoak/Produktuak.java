package dambi.pojoak;
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

    /**
     * element that is going to be marshaled in the xml
     */
    @XmlElement( name = "Produktua" )
    public void setProduktuak( List<Produktua> produktuak )
    {
        this.produktuak = produktuak;
    }

    /**
     * This method is not used by jaxb, just used for business reasons. In the case that this class
     * would be generated using xml schemas definitions, this method has to be added to the
     * generated class or to some helper or util one
     * 
     * @param produktua
     */
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
