package eric.cn.com.varnish.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraUtils {

    private static final String TAG = "CameraUtils";

    Activity mContext;

    private String[] provinces = new String[]{"照相", "本地图片", "取消"};

    private static final int TAKE_PICTURE = 0;

    private static final int CHOOSE_PICTURE = 1;

    private static final int SCALE = 5;// 照片缩小比例

    File cameraFile;

    String fileName;

    Handler mHandler;

    private File file;

    public CameraUtils(Activity context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;

        this.fileName = String.valueOf(System.currentTimeMillis());
        file = new File(Environment.getExternalStorageDirectory(), "image.png");
    }

    public void CameraToImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PICTURE:
                        Log.i(TAG, "照像机");
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        mContext.startActivityForResult(openCameraIntent, TAKE_PICTURE);


//					Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//					ContentValues contentValues = new ContentValues(1);
//					contentValues.put(MediaStore.Images.Media.DATA, new File(Environment.getExternalStorageDirectory(), "image.png").getAbsolutePath());
//					Uri imageUri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
//					openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//					mContext.startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                    case CHOOSE_PICTURE:
                        Log.i(TAG, "本地相册");

                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        mContext.startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.create().show();
    }

    public void MyCamera() {
        Log.i(TAG, "照像机");
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png"));
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        mContext.startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    public void MyPicture() {
        Log.i(TAG, "本地相册");
        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        mContext.startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
    }

    @SuppressLint("SdCardPath")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "********(((");
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    Log.i(TAG, "照像机1111");
                    // 将保存在本地的图片取出并缩小后显示在界面上
                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.png");
                    Bitmap newBitmap = ImageTools.comp(bitmap);
                    bitmap.recycle();
                    // 将处理过的图片显示在界面上，并保存到本地
                    String path = mContext.getApplicationContext().getFilesDir().getAbsolutePath();
                    ImageTools.savePhotoToSDCard(newBitmap, path, fileName);
                    cameraFile = new File(path, fileName + ".jpg");
                    message();
                    break;
                case CHOOSE_PICTURE:
                    Log.i(TAG, "本地相册111");
                    ContentResolver resolver = mContext.getContentResolver();
                    // 照片的原始资源地址
                    Uri originalUri = data.getData();
                    try {
                        // 使用ContentprovLister通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            // 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                            Bitmap smallBitmap = ImageTools.comp(photo);
                            // 释放原始图片占用的内存，防止out of memory异常发生
                            photo.recycle();
                            String path1 = mContext.getApplicationContext().getFilesDir().getAbsolutePath();
                            ImageTools.savePhotoToSDCard(smallBitmap, path1, fileName);
                            cameraFile = new File(path1, fileName + ".jpg");
                            message();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        }
    }
    private void message() {
        Message message = mHandler.obtainMessage();
        message.what = MsgConfig.MSG_WHAT_Camera;
        Bundle bundle = new Bundle();
        bundle.putSerializable("CaFile", cameraFile);

        message.setData(bundle);
        if (null != mHandler)
            mHandler.sendMessage(message);
    }

}
