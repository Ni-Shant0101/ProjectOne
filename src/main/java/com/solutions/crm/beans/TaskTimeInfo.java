package com.solutions.crm.beans;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskTimeInfo {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private List<LocalDateTime> startTimes;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private List<LocalDateTime> endTimes;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = " HH:mm:ss")
	private long totalDuration;
	
	private long totalBreakTime;

	public String formatDuration(long seconds) {
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		long remainingSeconds = seconds % 60;

		return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
	}

	public void calculateTotalDurationAndBreakTime() {
        totalDuration = 0;
        totalBreakTime = 0;

        if (startTimes != null && endTimes != null && startTimes.size() == endTimes.size()) {
            for (int i = 0; i < startTimes.size(); i++) {
                LocalDateTime startTime = startTimes.get(i);
                LocalDateTime endTime = endTimes.get(i);

                // Calculate duration for this session
                long sessionDurationSeconds = Duration.between(startTime, endTime).getSeconds();
                totalDuration += sessionDurationSeconds;

                // Calculate break time if it's not the first session
                if (i > 0) {
                    LocalDateTime previousEndTime = endTimes.get(i - 1);

                    // Calculate break duration
                    long breakDurationSeconds = Duration.between(previousEndTime, startTime).getSeconds();

                    // If break duration is negative, it means there's overlap, so set break duration to 0
                    if (breakDurationSeconds > 0) {
                        totalBreakTime += breakDurationSeconds;
                    }
                }
            }
        }
    }
	

	public TaskTimeInfo() {
		super();
	}

	public TaskTimeInfo(List<LocalDateTime> startTimes, List<LocalDateTime> endTimes, long totalDuration,
			long totalBreakTime) {
		super();
		this.startTimes = startTimes;
		this.endTimes = endTimes;
		this.totalDuration = totalDuration;
		this.totalBreakTime = totalBreakTime;
	}

	public List<LocalDateTime> getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(List<LocalDateTime> startTimes) {
		this.startTimes = startTimes;
	}

	public List<LocalDateTime> getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(List<LocalDateTime> endTimes) {
		this.endTimes = endTimes;
	}

	public String getTotalDuration() {
		return formatDuration(totalDuration);
	}

	public void setTotalDuration(long totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getTotalBreakTime() {
		return formatDuration(totalBreakTime);
	}

	public void setTotalBreakTime(long totalBreakTime) {
		this.totalBreakTime = totalBreakTime;
	}
}
