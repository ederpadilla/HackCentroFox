package androiddeveloper.eder.padilla.hackcentrofox;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
//transporte independiente

    @BindView(R.id.help_transport)
    ImageView mHelpTransport;

    @BindView(R.id.help_buy)
    ImageView mHelpBuy;

    @BindView(R.id.help_sell)
    ImageView mHelpSell;

    @BindView(R.id.help_donate)
    ImageView mHelpDonate;


    private FirebaseAuth mAuth;

    private String uUId;


    public static TextToSpeech textToSpeech;

    private static int PHOTO_TO_SELL = 120;

    private Bitmap bitmap;

    public static MaterialDialog calendarDialog , categoryDialog,thanksDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        textToSpeech =new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("es","MX"));
                }
            }
        });
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimary));
        }
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Util.log("onAuthStateChanged:signed_in: " + user.getUid());
            uUId =user.getUid();
        } else {
            // User is signed out
            Util.log("onAuthStateChanged:signed_out");
        }
    }

    @OnClick(R.id.help_transport)
    public void helpTransport(){
        textToSpeech.speak("Pública o busca un ruta de transporte independiente", TextToSpeech.QUEUE_FLUSH, null);
    }


    @OnClick(R.id.help_buy)
    public void helpBuy(){
        textToSpeech.speak("Compra justo lo que necesitas al precio más accesible.", TextToSpeech.QUEUE_FLUSH, null);
    }

    @OnClick(R.id.help_sell)
    public void helpSell(){
        textToSpeech.speak("¡Explota hasta el último centavo de tu esfuerzo!", TextToSpeech.QUEUE_FLUSH, null);
    }

    @OnClick(R.id.help_donate)
    public void helpDonate(){
        textToSpeech.speak("Tus posibles desperdicios pueden ser grandes beneficios", TextToSpeech.QUEUE_FLUSH, null);
    }

    @OnClick(R.id.tv_transport)
    public void tvTransport(){
        transport();
    }


    @OnClick(R.id.img_transport)
    public void imgTransport(){
        transport();
    }

    @OnClick(R.id.tv_buy)
    public void tvBuy(){
        buy();
    }

    @OnClick(R.id.img_buy)
    public void imgBuy(){
        buy();
    }


    @OnClick(R.id.sell_product)
    public void sellProduct(){
        sell();
    }

    @OnClick(R.id.img_sell)
    public void imgSell(){
        sell();
    }

    @OnClick(R.id.tv_donate)
    public void tvDonate(){
        donate();
    }

    @OnClick(R.id.img_donate)
    public void imgDonate(){
        donate();
    }

    private void transport() {
        new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.rout)
                .content(R.string.looking_routes)
                .positiveText(R.string.search)
                .positiveColorRes(R.color.colorPrimary)
                .negativeText(R.string.publish)
                .negativeColorRes(R.color.colorPrimaryDark)
                .cancelable(true)
                .onPositive((dialog, which) -> search(dialog))
                .onNegative((dialog, which) -> publish(dialog))
                .show();
    }

    private void publish(MaterialDialog dialog) {
        dialog.dismiss();
        Intent intent = new Intent(this,PublicarRutaActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void search(MaterialDialog dialog) {
        dialog.dismiss();
        Intent intent = new Intent(this,BuscarRutasActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void buy() {
        Util.log("si entra a buy");
    }

    private void sell() {
        textToSpeech.speak("Selecciona la imagen del producto", TextToSpeech.QUEUE_FLUSH, null);
        try {
            Intent chooseImageIntent = ImagePicker.getPickImageIntent(getApplicationContext());
            this.startActivityForResult(chooseImageIntent, PHOTO_TO_SELL);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(),getString(R.string.install_app),
                    Toast.LENGTH_SHORT).show();
        }
        Util.log("si entra a sell");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 120:
                if (resultCode == RESULT_OK) {
                    new AsyncTask<Void,Void,Void>(){

                        @Override
                        protected Void doInBackground(Void... voids) {
                            bitmap = ImagePicker.getImageFromResult(getApplicationContext(), resultCode, data);
                            bitmap = Bitmap.createScaledBitmap(bitmap,320,320,false);

                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                            byte[] byteArray = byteArrayOutputStream.toByteArray();


                            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                            String bytes = byteArray.toString();
                            //String encoded = encodeToBase64(bitmap,Bitmap.CompressFormat.PNG,100);

                            //imgBase64.add(encoded);
                            //base64= ImageUtil.convert(bitmap);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            //imageView.setImageBitmap(bitmap);
                            Util.log("se obtiene la imagen");
                            dialogCaducir();


                            super.onPostExecute(aVoid);

                        }
                    }.execute();



                }
                break;
        }

    }

    private void dialogCaducir() {
        textToSpeech.speak("Selecciona fecha de caducidad", TextToSpeech.QUEUE_FLUSH, null);
        calendarDialog = new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.expire_date)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive((dialog, which) -> datePick(dialog))
                .show();

    }

    private void datePick(MaterialDialog dialog) {
        dialog.dismiss();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datepicker");
        categoryDialog = new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.category)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .input("Categoría","",(categoryDialog, input) -> setInput(categoryDialog,input))
                .onPositive((categoryDialog, which) -> categorySelected(categoryDialog))
                .show();
        categoryDialog.dismiss();
    }

    private void categorySelected(MaterialDialog categoryDialog) {
        textToSpeech.speak("Ingrese el nombre del producto", TextToSpeech.QUEUE_FLUSH, null);
         new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.name)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .input("Nombre","",(dialog, nameInput) -> setInput(dialog,nameInput))
                .onPositive((dialog, which) -> nameSelected(dialog))
                .show();
    }

    private void nameSelected(MaterialDialog dialog) {
        textToSpeech.speak("Por último ingrese el costo del producto.", TextToSpeech.QUEUE_FLUSH, null);
        new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.price)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .input("Costo","",(dialogPrice, priceInput) -> setPrice(dialogPrice,priceInput))
                .onPositive((dialogPrice, which) -> priceSelected(dialogPrice))
                .show();

    }

    private void priceSelected(MaterialDialog dialogPrice) {
        dialogPrice.dismiss();
    }

    private void setPrice(MaterialDialog dialogPrice, CharSequence priceInput) {

    }

    public void requestCategory(){
      new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.expire_date)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .input("Categoría","Tomate",(dialog, input) -> setInput(dialog,input))
                .onPositive((dialog, which) -> datePick(dialog))
                .show();
    }

    private void setInput(MaterialDialog dialog, CharSequence input) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    private void donate() {

        textToSpeech.speak("Seleccione el destino de la donación", TextToSpeech.QUEUE_FLUSH, null);
        new MaterialDialog.Builder(this)
                .title(R.string.donation_destiny)
                .items(R.array.preference_values)
                .itemsCallbackSingleChoice(0,(dialog, itemView, which, text) -> getChoice(text))
                .positiveText(R.string.choose)
                .onPositive((dialog, which) -> donationDestinySelcted(dialog))
                .show();
    }

    private void donationDestinySelcted(MaterialDialog dialog) {
        dialog.dismiss();
        textToSpeech.speak("Ingrese el concepto de la donación", TextToSpeech.QUEUE_FLUSH, null);
        new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.donationConcept)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .input("Concepto","",(dialogDonation, inputDonation) -> setDonationConcept(dialogDonation,inputDonation))
                .onPositive((dialogDonation, which) -> donationConcept(dialogDonation))
                .show();
    }

    private void setDonationConcept(MaterialDialog dialogDonation, CharSequence inputDonation) {

    }

    private void donationConcept(MaterialDialog dialogDonation) {
        dialogDonation.dismiss();
        textToSpeech.speak("Por íultimo ingrese la fecha de entrega porfavor.", TextToSpeech.QUEUE_FLUSH, null);
        DonationDate newFragment = new DonationDate();
        newFragment.show(getFragmentManager(), "donartionDate");
        thanksDialog  = new MaterialDialog.Builder(Main2Activity.this)
                .title(R.string.thanks)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive((dialog, which) -> donationSelected(dialog))
                .show();
        thanksDialog.dismiss();
    }

    private void donationSelected(MaterialDialog dialog) {
        dialog.dismiss();
    }

    private boolean getChoice(CharSequence text) {
     Util.log("text "+text);
        return true;
    }
}
