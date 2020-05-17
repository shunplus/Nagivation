package com.shunplus.navigation.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 该类用于调试调用输出log，尽量用默认的tag标签，即只传入msg内容，如遇到临时输出，可以用自定义的tag，不要直接用Log
 *
 * @author zzm 2011-5-18 下午02:50:51
 */
public class PLog {
    /**
     * 默认的tag
     */
    public static final String TAG = "Hobby";
    public static final String MANNY = "manny";
    public static final String ZEZHANG = "ze.zhang";
    /**
     * 显示调试输出的开
     */
    public static boolean DEBUGGING = true;

    public static void setDebug(boolean isDebug) {
        DEBUGGING = isDebug;
    }

    public static void d(String msg) {
        if (DEBUGGING) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUGGING) {
            if (tag == null || tag.length() == 0) {
                i(msg);
            } else {
                Log.d(tag, msg);
            }
        }
    }

    public static void e(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (DEBUGGING) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUGGING) {
            if (tag == null || tag.length() == 0) {
                i(msg);
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUGGING) {
            if (tag == null || tag.length() == 0) {
                i(msg);
            } else {
                Log.e(tag, msg, tr);
            }
        }
    }

    public static void i(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (DEBUGGING) {
            int segmentSize = 3 * 1024;
            long length = msg.length();
            // 长度小于等于限制直接打印
            if (length <= segmentSize) {
                Log.i(TAG, msg);
            } else {
                // 循环分段打印日志
                while (msg.length() > segmentSize) {
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.i(TAG, "  -------------------------------------------  \n" + logContent);
                }
                // 打印剩余日志
                Log.i(TAG, " end ------------------------------------------- end \n" + msg);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUGGING) {
            if (tag == null || tag.length() == 0) {
                i(msg);
            } else {
                int segmentSize = 3 * 1024;
                long length = msg.length();
                // 长度小于等于限制直接打印
                if (length <= segmentSize) {
                    Log.i(tag, msg);
                } else {
                    // 循环分段打印日志
                    while (msg.length() > segmentSize) {
                        String logContent = msg.substring(0, segmentSize);
                        msg = msg.replace(logContent, "");
                        Log.i(TAG, "  -------------------------------------------  \n" + logContent);
                    }
                    // 打印剩余日志
                    Log.i(TAG, "end ------------------------------------------- end\n" + msg);
                }
            }
        }
    }

    public static void v(String msg) {
        if (DEBUGGING) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUGGING) {
            if (tag == null || tag.length() == 0) {
                i(msg);
            } else {
                Log.v(tag, msg);
            }
        }
    }

    public static void w(String msg) {
        if (DEBUGGING) {
            Log.w(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUGGING) {
            if (tag == null || tag.length() == 0) {
                i(msg);
            } else {
                Log.w(tag, msg);
            }
        }
    }

//    public static void outPutLog(String log) {
//        RandomAccessFile raf = null;
//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
//        String currentDate = sdfDate.format(new Date(System.currentTimeMillis()));
//        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
//        String ee = dff.format(new Date());
//        File recordFile = new File(PathHolder.FILE_PLOG + currentDate + "-plog.txt");
//        try {
//            if (!recordFile.exists()) {
//                recordFile.getParentFile().mkdirs();
//                recordFile.createNewFile();
//            }
//            raf = new RandomAccessFile(recordFile, "rwd");
//            raf.seek(recordFile.length());
//            String strLog = ee + "--" + log + "\r\n";
//            raf.write(strLog.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (raf != null) {
//                    raf.close();
//                }
//            } catch (IOException e) {
//                System.out.println(TAG + e.toString());
//            }
//        }
//
//    }

}
