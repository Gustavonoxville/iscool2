package easii.com.br.iscoolapp.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edmodo.cropper.CropImageView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import easii.com.br.iscoolapp.R;
import easii.com.br.iscoolapp.constantes.Constante;
import easii.com.br.iscoolapp.services.ServiceUploadFoto;


public class CameraCorrecao extends AppCompatActivity {

    //VIEW
    private Button btCrop;
    //EXTRAS
    private String idProva = null;
    private Long idDoAluno = -1L;
    private CropImageView mCropImageView;
    private String listaDeAlunos = null;
    private Long idDisciplina = null;
    private int posicao = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_correcao);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        idDoAluno = bundle.getLong("EXTRA_ID_USUARIO");
        idProva = bundle.getString("EXTRA_ID_PROVAS");
        idDisciplina = bundle.getLong("EXTRA_ID_DISCIPLINA");
        listaDeAlunos = bundle.getString("EXTRA_LISTA_DE_ALUNOS");
        posicao = bundle.getInt("EXTRA_POSICAO");

        Log.i("LOG", idDoAluno + " " + idProva);

        mCropImageView = (CropImageView) findViewById(R.id.mCropImageView);
        btCrop = (Button) findViewById(R.id.btCrop);

        Log.i("LOG", "id dis " + idDisciplina);
        corrige();
    }

    private void corrige() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "isCoolFotos" + File.separator + "image.png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, 0);
    }

    public void cortaFoto(View v) {

        Toast.makeText(CameraCorrecao.this, "Aguarde...", Toast.LENGTH_LONG).show();

        if (v.getId() == R.id.btCrop) {

            btCrop.setEnabled(false);
            Matrix matrix = new Matrix();
            matrix.setRotate(mCropImageView.getRotation());
            Bitmap auxBitmap = Bitmap.createScaledBitmap(mCropImageView.getCroppedImage(),
                    mCropImageView.getCroppedImage().getWidth(),
                    mCropImageView.getCroppedImage().getHeight(),
                    true);
            auxBitmap = Bitmap.createBitmap(auxBitmap, 0, 0, auxBitmap.getWidth(), auxBitmap.getHeight(), matrix, true);

            storeImage(getResizedBitmap(auxBitmap, (auxBitmap.getWidth()) / 3, (auxBitmap.getHeight()) / 3));

            Log.i("LOG", "foto salva");

            Intent i = new Intent(this, ServiceUploadFoto.class);
            i.putExtra("idDoAluno", "" + idDoAluno);
            i.putExtra("idProva", "" + idProva);
            startService(i);

            btCrop.setEnabled(true);

            finish();
//            Intent intent = new Intent(this, TelaPerfilAlunoDoProfessor.class);
//
//            Bundle extras = new Bundle();
//            extras.putLong("EXTRA_ID_DISCIPLINA", idDisciplina);
//            extras.putString("EXTRA_LISTA_DE_ALUNOS", listaDeAlunos);
//            extras.putInt("EXTRA_POSICAO", posicao);
//            extras.putLong("EXTRA_ID_ALUNO", idDoAluno);
//            intent.putExtras(extras);
//
//            startActivity(intent);

           // startService(new Intent(getBaseContext(), ServiceUploadFoto.class));
        }

    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("TAG", "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("TAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("TAG", "Error accessing file: " + e.getMessage());
        }
    }

    private File getOutputMediaFile() {

        File mediaFile;

        mediaFile = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))+ "fotodaultimacorrecao.png");
       //mediaFile = new File(Environment.getExternalStorageDirectory() + File.separator + "isCoolFotos" + File.separator + "image.png");
       // mediaFile = new File(CameraCorrecao.this.getFilesDir(), "/imagecorrecao.png");
        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_CANCELED){
          finish();
        }
        switch (requestCode) {

            case 0:

                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "isCoolFotos" + File.separator + "image.png");

                mCropImageView.setImageBitmap(bitmap);
            case 1:
                break;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera_correcao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
