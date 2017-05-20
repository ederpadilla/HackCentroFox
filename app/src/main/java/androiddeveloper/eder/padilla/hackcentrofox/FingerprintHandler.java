package androiddeveloper.eder.padilla.hackcentrofox;

/**
 * Created by ederpadilla on 19/05/17.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FingerprintHandler  extends FingerprintManager.AuthenticationCallback {


    private Context context;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth mAuth;


    // Constructor
    public FingerprintHandler(Context mContext) {
        context = mContext;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString, false);
        Toast.makeText(context, "La huella no pertenece al propietario",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false);
        Toast.makeText(context, "La huella no pertenece al propietario",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Fingerprint Authentication succeeded.", true);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in

        } else {
            // User is signed out
            Util.log("onAuthStateChanged:signed_out");
        }
        mAuth.signInWithEmailAndPassword("eder.padilla97@gmail.com", "ede56789")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Util.log("signInWithEmail:onComplete:" + task.isSuccessful());
                Toast.makeText(context, "¡Huella dactilar con acceso!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, Main2Activity.class); //Replace HomeActivity with the name of your activity
                context.startActivity(i);
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Util.log("signInWithEmail:failed"+task.getException());
                    Toast.makeText(context, R.string.auth_failed,
                            Toast.LENGTH_SHORT).show();
                }

                // ...
            }
        });

        // ...

    }


    public void update(String e, Boolean success){
        Util.log(e);
        if(success){

        }
    }
}
