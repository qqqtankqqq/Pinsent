package com.pinsent.user.pinsent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeScannerActivity extends AppCompatActivity {
    private Activity activity;
    private ZXingScannerView mQrCodeScannerView;

    public static final String INTENT_QR_CONTENT = "INTENT_QR_CONTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        mQrCodeScannerView = new ZXingScannerView(activity);
        setContentView(mQrCodeScannerView);

        mQrCodeScannerView.startCamera();
        mQrCodeScannerView.setAutoFocus(true);
        mQrCodeScannerView.setResultHandler(resultHandler);
    }

    private ZXingScannerView.ResultHandler resultHandler = new ZXingScannerView.ResultHandler() {
        @Override
        public void handleResult(Result result) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString(INTENT_QR_CONTENT, result.getText());
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            mQrCodeScannerView.stopCamera();
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQrCodeScannerView.stopCamera();
    }
}
