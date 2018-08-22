package vvmstk.config.image;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import javafx.scene.image.ImageView;
import org.bson.types.Binary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

public class imageIO {

    public imageIO() {
    }

    public Image getImage(byte jpg[]) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(jpg));
    }

    public byte[]setImage(Image img) throws IOException{
        byte output[] = null;
        BufferedImage bufferedImage = new BufferedImage(168, 209, 1);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, 168, 209, null);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "jpeg", out);
            out.flush();
            output = out.toByteArray();
        }
        return output;
    }

    public  void scale(File src, int width, int height, File dest) throws IOException {
        BufferedImage bsrc = ImageIO.read(src);
        BufferedImage bdest =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bdest.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance((double)width/bsrc.getWidth(),(double)height/bsrc.getHeight());
        g.drawRenderedImage(bsrc,at);
        ImageIO.write(bdest,"JPG",dest);
    }

    public void getDropBox() throws DbxException, IOException{
        final String ACCESS_TOKEN = "6Zs4zatuOqoAAAAAAAA-LaLChcGvU3gl-4qtN8BaDcvmIPdivG2wIGfbpo6DkcXn";
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
                FileOutputStream outputStream = new FileOutputStream("scan_img/tmp.jpg");
                FileMetadata metad = client.files().downloadBuilder(metadata.getPathLower()).download(outputStream);
                outputStream.close();
                client.files().delete(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());

        }
    }

    public void getFoto(Binary foto, ImageView imageStudent) throws IOException {
        if (foto!=null){
            InputStream in = new ByteArrayInputStream(foto.getData());
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "png", new File("tmp_img/out.png"));
            imageStudent.setImage(new javafx.scene.image.Image(new File("tmp_img/out.png").toURI().toString()));
        }
    }


}
