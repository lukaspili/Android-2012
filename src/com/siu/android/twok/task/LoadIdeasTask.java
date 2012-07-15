package com.siu.android.twok.task;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 23/05/12
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */ /*
public class LoadIdeasTask {

        private ArticlesFragment1 activity;

        public TennisLoadTask(TennisMapActivity activity) {
        this.activity = activity;
    }

        @Override protected void onPreExecute () {
        activity.setSupportProgressBarIndeterminateVisibility(true);
    }

        @Override protected List<Tennis> doInBackground (Double...coords){
        Log.d(getClass().getName(), "TennisLoadTask");
        return DatabaseHelper.getInstance().getDaoSession().getTennisDao().loadAll();
    }

        @Override protected void onPostExecute (List < Tennis > tennises) {
        Log.d(getClass().getName(), "Tennises : " + tennises.size());
        activity.setSupportProgressBarIndeterminateVisibility(false);
        if (null != tennises && !tennises.isEmpty()) {
            activity.onTennisLoadSuccess(tennises);
        }
        activity.onTennisLoadFinish();
    }
        @Override protected void onCancelled (List < Tennis > centers) {
        activity.setSupportProgressBarIndeterminateVisibility(false);
    }

    public void setFragment(TennisMapActivity activity) {
        this.activity = activity;
    }
}         */

