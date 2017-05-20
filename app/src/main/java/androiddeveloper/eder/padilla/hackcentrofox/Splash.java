package androiddeveloper.eder.padilla.hackcentrofox;

import android.content.Intent;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

/**
 * Created by ederpadilla on 19/05/17.
 */

public class Splash extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash) {

			/* you don't have to override every property */
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimary));
        }
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimaryDark); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1200); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.ic_corn); //or any other drawable
        configSplash.setAnimLogoSplashDuration(800); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.BounceIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        configSplash.setTitleSplash("");
        configSplash.setAnimTitleDuration(300);


    }

    @Override
    public void animationsFinished() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    //9814548
    //admin
}