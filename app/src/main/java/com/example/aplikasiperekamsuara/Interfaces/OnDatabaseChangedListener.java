package com.example.aplikasiperekamsuara.Interfaces;

import com.example.aplikasiperekamsuara.Models.RecordingItem;

public interface OnDatabaseChangedListener
{
    void onNewDatabaseEntryAdded(RecordingItem recordingItem);
}
