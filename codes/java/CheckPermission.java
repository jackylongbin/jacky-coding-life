private void checkAndRequestPermission()
{
	//需要请求的权限列表
	List<String> permissions = new ArrayList<>();
	if (ContextCompat.checkSelfPermission(this,
			Manifest.permission.READ_EXTERNAL_STORAGE)
			!= PackageManager.PERMISSION_GRANTED) {

		// Should we show an explanation?
		// 判断是否已经授权
		if (ActivityCompat.shouldShowRequestPermissionRationale(this,
				Manifest.permission.READ_EXTERNAL_STORAGE)) {

			// Show an expanation to the user *asynchronously* -- don't block
			// this thread waiting for the user's response! After the user
			// sees the explanation, try again to request the permission.

		} else {

			// No explanation needed, we can request the permission.
			permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
		}

	}

	if (ContextCompat.checkSelfPermission(this,
			Manifest.permission.WRITE_EXTERNAL_STORAGE)
			!= PackageManager.PERMISSION_GRANTED) {

		// Should we show an explanation?
		if (ActivityCompat.shouldShowRequestPermissionRationale(this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

			// Show an expanation to the user *asynchronously* -- don't block
			// this thread waiting for the user's response! After the user
			// sees the explanation, try again to request the permission.

		} else {

			// No explanation needed, we can request the permission.
			permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
		}

	}

	//开始请求权限
	if(permissions.size() > 0) {
		ActivityCompat.requestPermissions(this,
				permissions.toArray(new String[permissions.size()]),
				PERMISSION_REQUEST_CODE);

		// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
		// app-defined int constant. The callback method gets the
		// result of the request.
	}
}

/**
*请求权限回调
*/
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
	super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	switch (requestCode)
	{
		case PERMISSION_REQUEST_CODE:
			if (grantResults.length > 0
					&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

				// permission was granted, yay! Do the
				// contacts-related task you need to do.

			} else {
				// permission denied, boo! Disable the
				// functionality that depends on this permission.
				Toast.makeText(this,getResources().getString(R.string.permission_denied),Toast.LENGTH_LONG).show();
				mHandler.sendEmptyMessageDelayed(MSG_APP_EXIT,2000);
			}
			break;
	}
}
