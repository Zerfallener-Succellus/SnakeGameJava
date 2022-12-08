import java.awt.Font;
import java.io.IOException;
import java.awt.FontFormatException;

public class FontManager {

    public Font carregarFonte(String pCaminhoFonte, int tipoFonte, int pTamanhoFonte) {

        Font minhaFonte = null;

        try {

            minhaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(pCaminhoFonte))
                    .deriveFont(tipoFonte, pTamanhoFonte);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (FontFormatException ex) {
            ex.printStackTrace();
        }

        return minhaFonte;
    }
}
