package com.konifar.fab_transformation.animation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class FabAnimatorPreL extends FabAnimator {

    @Override
    final void revealOff(final View fab, final View transformView, final RevealCallback callback) {
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(
                transformView,
                getCenterX(fab, transformView),
                getCenterY(fab, transformView),
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()),
                fab.getWidth());
        animator.setInterpolator(REVEAL_INTERPOLATOR);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                callback.onRevealStart();
            }

            @Override
            public void onAnimationEnd() {
                transformView.setVisibility(View.INVISIBLE);
                callback.onRevealEnd();
            }

            @Override
            public void onAnimationCancel() {
                //
            }

            @Override
            public void onAnimationRepeat() {
                //
            }
        });
        if (transformView.getVisibility() == View.VISIBLE) {
            animator.setDuration(REVEAL_ANIMATION_DURATION);
            animator.start();
            transformView.setEnabled(true);
        }
    }

    @Override
    final void revealOn(final View fab, final View transformView, final RevealCallback callback) {
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(
                transformView,
                getCenterX(fab, transformView),
                getCenterY(fab, transformView),
                fab.getWidth(),
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()));
        transformView.setVisibility(View.VISIBLE);
        animator.setInterpolator(FAB_INTERPOLATOR);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                callback.onRevealStart();
            }

            @Override
            public void onAnimationEnd() {
                callback.onRevealEnd();
            }

            @Override
            public void onAnimationCancel() {
                //
            }

            @Override
            public void onAnimationRepeat() {
                //
            }
        });
        if (transformView.getVisibility() == View.VISIBLE) {
            animator.setDuration(REVEAL_ANIMATION_DURATION);
            animator.start();
            transformView.setEnabled(true);
        }
    }

    @Override
    final void fabMoveOut(final View fab, final View transformView, final FabAnimationCallback callback) {
        ViewPropertyAnimator.animate(fab)
                .translationX(0)
                .translationY(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(FAB_ANIMATION_DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        callback.onAnimationStart();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        callback.onAnimationEnd();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        callback.onAnimationCancel();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        callback.onAnimationRepeat();
                    }
                })
                .start();
    }

    @Override
    final void fabMoveIn(final View fab, final View transformView, final FabAnimationCallback callback) {
        ViewPropertyAnimator.animate(fab)
                .translationX(getTranslationX(fab, transformView))
                .translationY(getTranslationY(fab, transformView))
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(FAB_ANIMATION_DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        callback.onAnimationStart();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        callback.onAnimationEnd();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        callback.onAnimationCancel();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        callback.onAnimationRepeat();
                    }
                })
                .start();
    }

}