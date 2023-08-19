package com.ziyao.devops.jenkins.model;

/**
 * This class is based on informations which can be extracted from an running
 * Jenkins instance via
 * <a href="http://jenkins-server/computer/api/schema">http://jenkins-server/
 * computer/api/schema</a>.
 *
 * @author zhangziyao
 */
public class Executor {

    private Job currentExecutable;
    // in XSD it's a reference to a class
    private Job currentWorkUnit;
    private Boolean idle;
    private Boolean likelyStuck;
    private int number;
    private int progress;

    public Job getCurrentExecutable() {
        return currentExecutable;
    }

    public void setCurrentExecutable(Job currentExecutable) {
        this.currentExecutable = currentExecutable;
    }

    public Job getCurrentWorkUnit() {
        return currentWorkUnit;
    }

    public void setCurrentWorkUnit(Job currentWorkUnit) {
        this.currentWorkUnit = currentWorkUnit;
    }

    public Boolean getIdle() {
        return idle;
    }

    public void setIdle(Boolean idle) {
        this.idle = idle;
    }

    public Boolean getLikelyStuck() {
        return likelyStuck;
    }

    public void setLikelyStuck(Boolean likelyStuck) {
        this.likelyStuck = likelyStuck;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currentExecutable == null) ? 0 : currentExecutable.hashCode());
        result = prime * result + ((currentWorkUnit == null) ? 0 : currentWorkUnit.hashCode());
        result = prime * result + ((idle == null) ? 0 : idle.hashCode());
        result = prime * result + ((likelyStuck == null) ? 0 : likelyStuck.hashCode());
        result = prime * result + number;
        result = prime * result + progress;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Executor other = (Executor) obj;
        if (currentExecutable == null) {
            if (other.currentExecutable != null)
                return false;
        } else if (!currentExecutable.equals(other.currentExecutable))
            return false;
        if (currentWorkUnit == null) {
            if (other.currentWorkUnit != null)
                return false;
        } else if (!currentWorkUnit.equals(other.currentWorkUnit))
            return false;
        if (idle == null) {
            if (other.idle != null)
                return false;
        } else if (!idle.equals(other.idle))
            return false;
        if (likelyStuck == null) {
            if (other.likelyStuck != null)
                return false;
        } else if (!likelyStuck.equals(other.likelyStuck))
            return false;
        if (number != other.number)
            return false;
        if (progress != other.progress)
            return false;
        return true;
    }

}
