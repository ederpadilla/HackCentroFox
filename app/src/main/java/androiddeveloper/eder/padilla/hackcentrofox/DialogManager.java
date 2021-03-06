package androiddeveloper.eder.padilla.hackcentrofox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by ederpadilla on 23/01/17.
 */

public class DialogManager {

    public static MaterialDialog showProgressDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title("Cargando")
                .content("Espere un momento")
                .cancelable(false)
                .progress(true, 0)
                .show();
    }

    public static MaterialDialog showErrorDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title("Upps..")
                .content("Lo sentimos algo esta saliendo mal intenta mas tarde.")
                .positiveText("ACEPTAR")
                .cancelable(false)
                .titleColorRes(R.color.colorPrimaryDark)
                .show();
    }

    public static MaterialDialog showErrorDialog(Activity context, String content) {
        return new MaterialDialog.Builder(context)
                .title("Upps..")
                .content(content)
                .positiveText(context.getString(R.string.accept))
                .cancelable(false)
                .onPositive((dialog, which) -> dialog.dismiss())
                .titleColorRes(R.color.colorPrimaryDark)
                .show();
    }

    public static void showSuccessDialogAndFinish(Activity activity) {
        new MaterialDialog.Builder(activity)
                .title("Bien")
                .content("Se ha completado con exito tu solicitud")
                .positiveText("Aceptar")
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        activity.finish();
                    }
                })
                .show();
    }

    public static void showSuccessDialogAndFinishAll(Activity activity, Class activityStart) {
        new MaterialDialog.Builder(activity)
                .title("Bien")
                .content("Se ha completado con exito tu solicitud")
                .positiveText("Aceptar")
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(activity, activityStart);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                    }
                })
                .show();
    }

    public static void showSuccessDialogAndFinishAll(Activity activity, String message, Class activityStart) {
        new MaterialDialog.Builder(activity)
                .title("Bien")
                .content(message)
                .positiveText("Aceptar")
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(activity, activityStart);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                    }
                })
                .show();
    }

    public static void showSuccessDialog(Context context) {
        new MaterialDialog.Builder(context)
                .title("Bien")
                .content("Se ha completado con exito tu solicitud")
                .positiveText("Aceptar")
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    }
                })
                .show();
    }

    public static void showSuccessDialog(Context context, String message) {
        new MaterialDialog.Builder(context)
                .title("Bien")
                .content(message)
                .positiveText("Aceptar")
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    }
                })
                .show();
    }
    public static void showSuccessfulDialog(Activity activity) {
        new MaterialDialog.Builder(activity)
                .title(R.string.great)
                .content(R.string.good_request)
                .positiveText(R.string.accept)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .onPositive((dialog, which) -> activity.finish())
                .show();
    }

    public static void showDeniedAccessDialog(Context context) {
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title("Acceso denegado")
                .content("Ve a configuraciones y habilita los permisos de esta aplicación.")
                .positiveText("ACEPTAR")
                .titleColorRes(R.color.color_text_error)
                .show();
    }
    public static void noInternetAccessDialog(Activity activity){
        MaterialDialog materialDialog  = new MaterialDialog.Builder(activity)
                .title(R.string.no_internet_access)
                .content(R.string.need_internet)
                .positiveText(R.string.retry)
                .titleColorRes(R.color.colorAccent)
                .cancelable(false)
                .positiveColorRes(R.color.colorAccent)
                .onPositive((dialog, which) -> activity.finish())
                .show();
    }

    public static void noConectionWithServer(Activity activity){
        MaterialDialog materialDialog  = new MaterialDialog.Builder(activity)
                .title(R.string.ups)
                .content(R.string.no_conection)
                .titleColorRes(R.color.colorAccent)
                .cancelable(false)
                .positiveColorRes(R.color.colorAccent)
                .onPositive((dialog, which) -> activity.finish())
                .show();
    }

    public static void noSuccessDialog(Activity activity){
        MaterialDialog materialDialog  = new MaterialDialog.Builder(activity)
                .title(R.string.no_internet_access)
                .content(R.string.need_internet)
                .positiveText(R.string.retry)
                .titleColorRes(R.color.colorAccent)
                .cancelable(false)
                .positiveColorRes(R.color.colorAccent)
                .onPositive((dialog, which) -> activity.finish())
                .show();
        Main2Activity.calendarDialog.dismiss();
    }
//    public static MaterialDialog showErrorDialog(Context context) {
//        return new MaterialDialog.Builder(context)
//                .title("Upps..")
//                .content("Lo sentimos algo esta saliendo mal intenta mas tarde.")
//                .positiveText("ACEPTAR")
//                .titleColorRes(R.color.color_text_error)
//                .show();
//    }
//
//    public static MaterialDialog showErrorDialog(Context context, String content) {
//        return new MaterialDialog.Builder(context)
//                .title("Upps..")
//                .content(content)
//                .positiveText("ACEPTAR")
//                .titleColorRes(R.color.color_text_error)
//                .show();
//    }

}